import React, {Component} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";

class DialogUtil extends Component {

    constructor() {
        super();
        this.state = {
            storeSeq: null,
            vtelnum: null,
            prologueUrl: null,
            menuUrl: null,
            epilogueUrl: null,
            useYn: null
        };
    };

    changeData(event) {
        this.setData(event.target.id, event.target.id == "useYn" ? event.target.checked : event.target.value);
    }

    componentWillReceiveProps() {
        if (this.props.data.type == 'edit') {
            this.setState({storeSeq: this.props.data.data.seq});
            this.setData("vtelnum", this.props.data.data.vtelnum);
            this.setData("prologueUrl", this.props.data.data.storeCallgateIps == null ? null : this.props.data.data.storeCallgateIps.prologueUrl);
            this.setData("menuUrl", this.props.data.data.storeCallgateIps == null ? null : this.props.data.data.storeCallgateIps.menuUrl);
            this.setData("epilogueUrl", this.props.data.data.storeCallgateIps == null ? null : this.props.data.data.storeCallgateIps.epilogueUrl);
        }
    }

    setData(type, value) {
        if (type == "vtelnum") {
            this.setState({vtelnum: value});
        }
        else if (type == "prologueUrl") {
            this.setState({prologueUrl: value});
        }
        else if (type == "menuUrl") {
            this.setState({menuUrl: value});
        }
        else if (type == "epilogueUrl") {
            this.setState({epilogueUrl: value});
        }
        else if (type == "useYn") {
            this.setState({useYn: value});
        }
    }

    render() {
        let dialog;
        if (this.props.data.type == 'view') {
            dialog = (
                <Modal isOpen={this.props.data.large} toggle={this.props.toggleEvent}
                       className={'modal-lg ' + this.props.className}>
                    <ModalHeader toggle={this.props.toggleEvent}>{this.props.data.title}</ModalHeader>
                    <ModalBody>
                        <div>
                            <img src={this.props.data.data} className="img-avatar"
                                 style={{maxWidth: 100 + '%', maxHeight: 100 + '%'}}/>
                        </div>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.props.toggleEvent}>확인</Button>{' '}
                    </ModalFooter>
                </Modal>
            );
        }
        else if (this.props.data.type == 'edit') {
            dialog = (
                <Modal isOpen={this.props.data.large} toggle={this.props.toggleEvent}
                       className={'modal-lg ' + this.props.className}>
                    <ModalHeader toggle={this.props.toggleEvent}>수정</ModalHeader>
                    <ModalBody style={{maxHeight: 500 + 'px', overflow: "auto"}}>
                        <Form>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">가맹점 코드</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Input type="text" id="storeSeq" name="text-input" placeholder="Text"
                                           value={this.props.data.data.seq} readOnly/>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">가맹점 명</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Input type="text" id="storeName" name="text-input" placeholder="Text"
                                           value={this.props.data.data.name} readOnly/>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">가상 전화번호</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Input type="text" id="vtelnum" name="text-input" placeholder=""
                                           defaultValue={this.props.data.data.vtelnum}
                                           onChange={this.changeData.bind(this)}/>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">프롤로그 링크</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Input type="text" id="prologueUrl" name="text-input" placeholder=""
                                           defaultValue={this.props.data.data.storeCallgateIps == null ?
                                               '' : this.props.data.data.storeCallgateIps.prologueUrl}
                                           onChange={this.changeData.bind(this)}
                                    />
                                    <img src={this.state.prologueUrl} className="img-avatar"
                                         style={{maxWidth: 80 + '%', maxHeight: 80 + '%', marginTop: 16 + 'px'}}/>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">메뉴 링크</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Input type="text" id="menuUrl" name="text-input" placeholder=""
                                           defaultValue={this.props.data.data.storeCallgateIps == null ?
                                               '' : this.props.data.data.storeCallgateIps.menuUrl}
                                           onChange={this.changeData.bind(this)}/>
                                    <img src={this.state.menuUrl} className="img-avatar"
                                         style={{maxWidth: 80 + '%', maxHeight: 80 + '%', marginTop: 16 + 'px'}}/>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">에필로그 링크</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Input type="text" id="epilogueUrl" name="text-input" placeholder=""
                                           defaultValue={this.props.data.data.storeCallgateIps == null ?
                                               '' : this.props.data.data.storeCallgateIps.epilogueUrl}
                                           onChange={this.changeData.bind(this)}/>
                                    <img src={this.state.epilogueUrl} className="img-avatar"
                                         style={{maxWidth: 80 + '%', maxHeight: 80 + '%', marginTop: 16 + 'px'}}/>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md="3">
                                    <Label htmlFor="text-input">사용여부</Label>
                                </Col>
                                <Col xs="12" md="9">
                                    <Label className="switch switch-text switch-primary">
                                        <Input type="checkbox" id="useYn" className="switch-input"
                                               defaultChecked={this.props.data.data.useYn == "Y" ? true : false}
                                               onChange={this.changeData.bind(this)}/>
                                        <span className="switch-label" data-on="ON" data-off="OFF"></span>
                                        <span className="switch-handle"></span>
                                    </Label>
                                </Col>
                            </FormGroup>
                        </Form>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={() => this.props.saveEvent(this.state)}>저장</Button>{' '}
                        <Button color="secondary" onClick={this.props.toggleEvent}>취소</Button>
                    </ModalFooter>
                </Modal>
            );
        }
        else {
            dialog = (
                <div></div>
            );
        }

        return (
            <div>
                {dialog}
            </div>
        )
    }
}

export default DialogUtil;