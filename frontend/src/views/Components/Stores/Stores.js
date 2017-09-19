import React, {Component} from "react";
import 'whatwg-fetch';
import {
    Row,
    Col,
    Card,
    CardHeader,
    CardBlock,
    Form,
    FormGroup,
    Button,
    Table,
    Input,
    Pagination,
    PaginationItem,
    PaginationLink,
    Badge
} from "reactstrap";
import DialogUtil from "../../../components/Util/DialogUtil";

const LIST_URL = "http://localhost:21110/callgate-admin/stores?size=30&page={page}&sort=seq,desc";
const SAVE_URL = "http://localhost:21110/callgate-admin/stores";
const LINK_URL = "http://localhost:21110/callgate-admin/callgate/{storeSeq}";
const API_HEADERS = {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
};

class Stores extends Component {

    constructor() {
        super();
        this.state = {
            stores: [],
            page: {
                isPrevious: false,
                isNext: false,
                currentPage: 1,
                arrPage: []
            },
            q: null,
            dialog: {
                large: false,
                type: null,
                title: null,
                data: null
            }
        };

        this.toggleLarge = this.toggleLarge.bind(this);
        this.saveData = this.saveData.bind(this);
    };

    componentDidMount() {
        this.getData(1);
    };

    movePage(page, event) {
        this.getData(page);
    };

    getData(page) {
        fetch(LIST_URL.replace('{page}', page), {headers: API_HEADERS})
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({stores: responseData.data, page: responseData.pageUtil});
            })
            .catch((error) => {
                alert(error.toString());
            });
    };

    saveData(data) {
        fetch(SAVE_URL, {
            method: 'POST',
            headers: API_HEADERS,
            body: JSON.stringify(data)
        })
            .then(function(response) {
                if (response.ok) {
                    alert('저장완료');
                    this.toggleLarge();
                    this.getData(this.state.page.currentPage);
                }
                else {
                    throw new Error("Server response wasn't OK");
                }
            }.bind(this))
            .catch((error) => {
                alert(error.toString());
            });

    }

    linkData(type, storeSeq) {

        fetch(LINK_URL.replace('{storeSeq}', storeSeq), {
            method: type,
            headers: API_HEADERS
        })
            .then(function(response) {
                if (response.ok) {
                    alert('연동완료');
                    this.toggleLarge();
                    this.getData(this.state.page.currentPage);
                }
                else {
                    throw new Error("Server response wasn't OK");
                }
            }.bind(this))
            .catch((error) => {
                alert(error.toString());
            });
    }

    openPopup(type, data, event) {
        var dialog = this.state.dialog;
        dialog.data = data;
        dialog.type = type;
        this.toggleLarge();
    };

    toggleLarge() {
        var dialog = this.state.dialog;
        dialog.large = !dialog.large;
        this.setState({
            dialog: dialog
        });
    }

    render() {

        const th50Style = {width: 50 + 'px'};
        const th200Style = {width: 200 + 'px'};
        const th300Style = {width: 300 + 'px'};
        const th120Style = {width: 120 + 'px'};
        const th100Style = {width: 100 + 'px'};
        const th80Style = {width: 80 + 'px'};
        const tdCol30Style = {width: 80 + 'px', padding: 0 + 'px', border: 0 + 'px'};
        const tdCol50Style = {width: 60 + 'px', padding: 0 + 'px', border: 0 + 'px'};
        const tdStyle = {padding: 0 + 'px', paddingLeft: 10 + 'px', border: 0 + 'px', marginRight: 10 + 'px'};
        const btnLink = {padding: 0 + 'px', border: 0 + 'px'};
        const badge80Style={width: 80 + 'px'};

        return (
            <div className="animated fadeIn">
                <Row>
                    <Col>
                        <Card>
                            <CardHeader>
                                <i className="fa fa-align-justify"></i> 가맹점 목록
                            </CardHeader>
                            <CardBlock>
                                <DialogUtil data={this.state.dialog} toggleEvent={this.toggleLarge}
                                            saveEvent={this.saveData}/>
                            </CardBlock>
                            <CardBlock className="card-body" style={{paddingBottom: 0 + 'px'}}>
                                <Form action="" method="post" inline>
                                    <FormGroup>
                                        <Input type="select" name="select" id="select" style={{marginRight: 5 + 'px'}}>
                                            <option value="0">가맹점명</option>
                                            <option value="1">지사명</option>
                                        </Input>
                                        <Input type="text" placeholder="검색어" required style={{height: 36 + 'px'}}/>
                                    </FormGroup>
                                </Form>
                            </CardBlock>
                            <CardBlock className="card-body">
                                <Table responsive>
                                    <thead>
                                    <tr>
                                        <th style={th120Style}>가맹점코드</th>
                                        <th style={th100Style}>지사명</th>
                                        <th style={th100Style}>가맹점명</th>
                                        <th>정보</th>
                                        <th style={th300Style}>기능</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {this.state.stores != null && this.state.stores.map((store, i) =>
                                        <tr key={store.seq}>
                                            <td>{store.seq}</td>
                                            <td>{store.storeOwner.name}</td>
                                            <td>{store.name}</td>
                                            <td>
                                                {store.storeCallgateIps != null &&
                                                <table>
                                                    <tbody>
                                                    <tr>
                                                        <td style={tdCol50Style}>
                                                            <Badge color="success" style={badge80Style}>가상 전화번호</Badge>
                                                        </td>
                                                        <td style={tdStyle}>
                                                            {store.vtelnum}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style={tdCol50Style}>
                                                            <Badge color="success" style={badge80Style}>프롤로그 링크</Badge>
                                                        </td>
                                                        <td style={tdStyle}>
                                                            {store.storeCallgateIps.prologueUrl}
                                                            {/*<Button color="link" style={btnLink}*/}
                                                            {/*onClick={(event) => this.openPopup('view', store.storeCallgateIps.prologueUrl, event)}>*/}
                                                            {/*{store.storeCallgateIps.prologueUrl}*/}
                                                            {/*</Button>{' '}*/}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style={tdCol50Style}>
                                                            <Badge color="success" style={badge80Style}>메뉴 링크</Badge>
                                                        </td>
                                                        <td style={tdStyle}>
                                                            {store.storeCallgateIps.menuUrl}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style={tdCol50Style}>
                                                            <Badge color="success" style={badge80Style}>에필로그링크</Badge>
                                                        </td>
                                                        <td style={tdStyle}>
                                                            {store.storeCallgateIps.epilogueUrl}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style={tdCol50Style}>
                                                            <Badge color="success" style={badge80Style}>사용여부</Badge>
                                                        </td>
                                                        <td style={tdStyle}>
                                                            {store.storeCallgateIps.useYn == "Y" ? "사용" : "미사용"}
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                }
                                            </td>
                                            <td>
                                                <Button color="secondary"
                                                        onClick={(event) => this.openPopup('edit', store, event)}>수정</Button>{' '}
                                                <Button color="success"
                                                        onClick={(event) => this.linkData('POST', store.seq)}>연동하기</Button>{' '}
                                                <Button color="danger"
                                                        onClick={(event) => this.linkData('DELETE', store.seq)}>연동끊기</Button>{' '}
                                            </td>
                                        </tr>
                                    )}
                                    </tbody>
                                </Table>
                                {this.state.page != null &&
                                <Pagination>
                                    {this.state.page.isPrevious &&
                                    <PaginationItem>
                                        <PaginationLink previous
                                                        onClick={this.movePage.bind(this, this.state.page.arrPage[0] - 1)}></PaginationLink>
                                    </PaginationItem>
                                    }
                                    {this.state.page.arrPage.map(function (page) {
                                            return (
                                                this.state.page.currentPage == page ? (
                                                    <PaginationItem active>
                                                        <PaginationLink
                                                            onClick={this.movePage.bind(this, page)}>{page}</PaginationLink>
                                                    </PaginationItem>
                                                ) : (
                                                    <PaginationItem>
                                                        <PaginationLink
                                                            onClick={this.movePage.bind(this, page)}>{page}</PaginationLink>
                                                    </PaginationItem>
                                                )
                                            )
                                        }, this
                                    )}
                                    <PaginationItem>
                                        {this.state.page.isNext &&
                                        <PaginationLink next
                                                        onClick={this.movePage.bind(this, this.state.page.arrPage[this.state.page.arrPage.length - 1] + 1)}></PaginationLink>
                                        }
                                    </PaginationItem>
                                </Pagination>
                                }
                            </CardBlock>
                        </Card>
                    </Col>
                </Row>
            </div>
        )
    }
}

export default Stores;
