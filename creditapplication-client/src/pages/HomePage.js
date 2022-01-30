import React, { useState } from "react";
import { createCustomer } from "../api/apiCall";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import dateFormat from "dateformat";
import { Button, Modal, Form, Row, Col, Alert } from "react-bootstrap";
import moment from "moment";

export default function HomePage() {
  const [name, setName] = useState("Samet");
  const [lastName, setLastName] = useState("Kesici");
  const [phoneNumber, setPhoneNumber] = useState();
  const [monthlyIncome, setMonthlyIncome] = useState();
  const [assurance, setAssurance] = useState(0);
  const [identificationNumber, setIdentificationNumber] = useState();
  const [birthDate, setBirthDate] = useState(new Date());
  const [birthDateRequest, setBirthDateRequest] = useState(
    moment(birthDate).format("YYYY-MM-DD")
  );
  const [errorResponse, setErrorResponse] = useState();
  const [creditLimit, setCreditLimit] = useState();
  const [applicationStatus, setApplicationStatus] = useState();
  const [showAlert, setShowAlert] = useState(false);

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  let phoneNumberWithPlus = "";

  const onClickCreate = async (event) => {
    event.preventDefault();
    setShowAlert(false);
    phoneNumberWithPlus += "+" + phoneNumber;
    console.log(phoneNumberWithPlus);
    const creds = {
      name,
      lastName,
      phoneNumber: phoneNumberWithPlus,
      monthlyIncome,
      identificationNumber,
      assurance,
      birthDate: birthDateRequest,
    };

    try {
      const response = await createCustomer(creds);
      setCreditLimit(response.data.creditLimit);
      setApplicationStatus(response.data.applicationStatus);
      handleShow();
    } catch (error) {
      setShowAlert(true);
      setErrorResponse(error.response.data.message);
    }
  };

  const setDate = (date) => {
    setBirthDate(date);
    setBirthDateRequest(moment(date).format("YYYY-MM-DD"));
  };

  return (
    <div
      className="container"
      style={{
        height: "70vh",
        width: "",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Form onSubmit={onClickCreate}>
        <Row className="mb-3">
          <Form.Group as={Col} md="4" controlId="validationCustom01">
            <Form.Label>İsim</Form.Label>
            <Form.Control
              onChange={(event) => setName(event.target.value)}
              required
              type="text"
              placeholder="İsim"
              defaultValue="Samet"
            />
          </Form.Group>
          <Form.Group as={Col} md="4" controlId="validationCustom02">
            <Form.Label>Soy İsim</Form.Label>
            <Form.Control
              onChange={(event) => setLastName(event.target.value)}
              required
              type="text"
              placeholder="Soyisim"
              defaultValue="Kesici"
            />
          </Form.Group>
          <Form.Group as={Col} md="4" controlId="validationCustom03">
            <Form.Label>Kimlik Numarası</Form.Label>
            <Form.Control
              onChange={(event) => setIdentificationNumber(event.target.value)}
              required
              type="number"
            />
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} md="6" controlId="validationCustom04">
            <Form.Label>Telefon Numarası</Form.Label>
            <Form.Control
              onChange={(event) => setPhoneNumber(event.target.value)}
              type="number"
              required
            />
          </Form.Group>
          <Form.Group as={Col} md="3" controlId="validationCustom05">
            <Form.Label>Aylık Maaş</Form.Label>
            <Form.Control
              onChange={(event) => setMonthlyIncome(event.target.value)}
              type="number"
              required
            />
          </Form.Group>
          <Form.Group as={Col} md="3" controlId="validationCustom06">
            <Form.Label>Teminat(Opsiyonel)</Form.Label>
            <Form.Control
              onChange={(event) => setAssurance(event.target.value)}
              type="number"
            />
          </Form.Group>
        </Row>
        <Row>
          <Form.Group as={Col} md="6">
            <Form.Label>Doğum Tarihi</Form.Label>
            <DatePicker
              selected={birthDate}
              dateFormat="yyyy/MM/dd"
              onChange={(date) => setDate(date)}
            />
          </Form.Group>
        </Row>
        <Button className="mt-3" type="submit">
          Kaydet
        </Button>

        <Alert
          className="mt-3"
          show={showAlert}
          variant="warning"
          onClose={() => setShowAlert(false)}
          dismissible
        >
          Error : {errorResponse}
        </Alert>
      </Form>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>Kredi başvurusu : {applicationStatus}</h4>
          <h4>Kredi Limiti : {creditLimit}</h4>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleClose}>
            Kapat
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}
