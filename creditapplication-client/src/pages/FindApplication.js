import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { findApplication } from "../api/apiCall";
import { Button, Modal, Alert } from "react-bootstrap";
import moment from "moment";

function FindApplication() {
  const [birthDate, setBirthDate] = useState(new Date());
  const [birthDateRequest, setBirthDateRequest] = useState(
    moment(birthDate).format("YYYY-MM-DD")
  );
  const [identificationNumber, setIdentificationNumber] = useState();
  const [errorResponse, setErrorResponse] = useState();
  const [showAlert, setShowAlert] = useState(false);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [applicationStatus, setApplicationStatus] = useState("");
  const [creditLimit, setCreditLimit] = useState(0);

  const onClickFind = async (event) => {
    event.preventDefault();
    try {
      const response = await findApplication(
        birthDateRequest,
        identificationNumber
      );
      handleShow();
      console.log(response.data);
      setName(response.data.customer.name);
      setLastName(response.data.customer.lastName);
      setApplicationStatus(response.data.applicationStatus);
      setCreditLimit(response.data.creditLimit);
    } catch (error) {
      setErrorResponse(error.response.data.message);
      setShowAlert(true);
    }
  };

  const setDate = (date) => {
    setBirthDate(date);
    setBirthDateRequest(moment(date).format("YYYY-MM-DD"));
  };

  console.log(birthDateRequest);

  return (
    <div
      className="container mt-5"
      style={{
        height: "50vh",
        width: "",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div>
        <form
          onSubmit={onClickFind}
          className="row g-3 needs-validation"
          noValidate
        >
          <div className="col-md-4 mt-3">
            <label htmlFor="validationCustom02" className="form-label">
              Kimlik Numarası
            </label>
            <input
              onChange={(event) => setIdentificationNumber(event.target.value)}
              type="text"
              className="form-control"
              id="validationCustom02"
              required
            />
            <div className="valid-feedback">Looks good!</div>
          </div>
          <div className="col-md-12 mt">
            <label htmlFor="validationCustom01" className="form-label">
              Doğum Tarihi
            </label>
            <DatePicker
              selected={birthDate}
              onChange={(date) => setDate(date)}
              dateFormat="yyyy/MM/dd"
            />
            <div className="valid-feedback">Looks good!</div>
          </div>
          <div className="col-12">
            <button
              data-toggle="modal"
              data-target="#exampleModalCenter"
              className="btn btn-primary"
              type="submit"
            >
              Başvuru Ara
            </button>
            <Alert
              onClose={() => setShowAlert(false)}
              className="mt-3"
              show={showAlert}
              variant="warning"
              dismissible
            >
              Error : {errorResponse}
            </Alert>
          </div>
        </form>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Başvuru Sorgulama</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h4>
              Müşteri Adı ve Soyadı : {name} {lastName}
            </h4>
            <h4>Kredi Durumu : {applicationStatus}</h4>
            <h4>Kredi Limiti : {creditLimit}</h4>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Kapat
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
}

export default FindApplication;
