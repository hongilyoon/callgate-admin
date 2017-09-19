import React, {Component} from "react";
import {Switch, Route, Redirect, Link} from 'react-router-dom';
import {Container, Row, Col, CardGroup, Card, CardBlock, Button, Input, InputGroup, InputGroupAddon} from "reactstrap";


class Login extends Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            loginSuccess: false
        }
    }

    handleChange(event) {
        if (event.target.placeholder == "Username") {
            this.setState({username: event.target.value});
        }
        else if (event.target.placeholder == "Password") {
            this.setState({password: event.target.value});
        }
    }

    loginBtnClicked(event) {
        this.setState({loginSuccess: true});
    }

    render() {
        if (this.state.loginSuccess) {
            return <Redirect push to="/dashboard"/>
        }

        return (
            <div className="app flex-row align-items-center">
                <Container>
                    <Row className="justify-content-center">
                        <Col md="8">
                            <CardGroup className="mb-0">
                                <Card className="p-4">
                                    <CardBlock className="card-body">
                                        <h1>Login</h1>
                                        <p className="text-muted">Sign In to your account</p>
                                        <InputGroup className="mb-3">
                                            <InputGroupAddon><i className="icon-user"></i></InputGroupAddon>
                                            <Input type="text" placeholder="Username" value={this.state.username}
                                                   onChange={this.handleChange.bind(this)}/>
                                        </InputGroup>
                                        <InputGroup className="mb-4">
                                            <InputGroupAddon><i className="icon-lock"></i></InputGroupAddon>
                                            <Input type="password" placeholder="Password" value={this.state.password}
                                                   onChange={this.handleChange.bind(this)}/>
                                        </InputGroup>
                                        <Row>
                                            <Col xs="6">
                                                <Button color="primary" className="px-4"
                                                        onClick={this.loginBtnClicked.bind(this)}>Login</Button>
                                            </Col>
                                            <Col xs="6" className="text-right">
                                                <Button color="link" className="px-0">Forgot password?</Button>
                                            </Col>
                                        </Row>
                                    </CardBlock>
                                </Card>
                            </CardGroup>
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default Login;
