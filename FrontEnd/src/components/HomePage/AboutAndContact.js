import React from "react";
import "../../css/AboutAndContact.css";
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

function AboutUs() {


    return (

        <div className="about-page">
            <div className="about-section" >
                <h1 >About Us</h1>
                <p>Welcome to AGME!</p>
                <p>We're dedicated to providing you the best of all services, with a focus on dependability. customer service, and luxury.
                We hope you enjoy our services as much as we enjoy offering them to you.
                </p>
                <p>Sincerely,
                AGME Team</p>
            </div>

            <h2 className="about-header"> Our Team</h2>

            <Container>
                <Row>
                    <Col>
                        <div className="card">
                            <h3>Austin Lay</h3>
                            <p>Development Team</p>
                            <p>Studying @ RMIT: Bachelor of Software Engineering (2nd Year)</p>
                            <p>s3779726@student.rmit.edu.au</p>
                        </div>
                    </Col>
                    <Col>
                        <div className="card">
                            <h3>Joshua</h3>
                            <p>Development Team</p>
                            <p>A Cool Guy</p>
                            <p>s3687337@student.rmit.edu.au</p>
                        </div>
                    </Col>
                    <Col>
                        <div className="card">
                            <h3>Danny</h3>
                            <p>Development Team</p>
                            <p>Some developer</p>
                            <p>s3601030@student.rmit.edu.au</p>
                        </div>
                    </Col>
                    <Col>
                        <div className="card">
                            <h3>Daniel</h3>
                            <p>Development Team</p>
                        </div>
                    </Col>
                    <Col>
                        <div className="card">
                            <h3>Umer</h3>
                            <p>Development Team</p>
                        </div>
                    </Col>
                </Row>
            </Container>


        </div>

    )

}



export default AboutUs;
