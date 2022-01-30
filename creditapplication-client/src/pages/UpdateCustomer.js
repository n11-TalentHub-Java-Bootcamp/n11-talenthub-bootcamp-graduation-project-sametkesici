import React, { useState } from "react";
import { updateCustomer } from "../api/apiCall";
import { Button, Modal } from "react-bootstrap";
import { Alert } from "react-bootstrap";

function UpdateCustomer() {
  const [monthlyIncome, setMonthlyIncome] = useState();
  const [assurance, setAssurance] = useState(0);
  const [identificationNumber, setIdentificationNumber] = useState();
  const [errorResponse, setErrorResponse] = useState();

  const [showAlert, setShowAlert] = useState(false);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [applicationStatus, setApplicationStatus] = useState("");
  const [creditLimit, setCreditLimit] = useState(0);

  const onClickUpdate = async (event) => {
    event.preventDefault();
    const creds = {
      monthlyIncome,
      identificationNumber,
      assurance,
    };

    try {
      const response = await updateCustomer(creds);
      console.log(response.data);
      setApplicationStatus(response.data.applicationStatus);
      setCreditLimit(response.data.creditLimit);
      handleShow();
    } catch (error) {
      setErrorResponse(error.response.data.message);
      setShowAlert(true);
      console.log(errorResponse);
    }
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
      <form
        onSubmit={onClickUpdate}
        className="row mt-5"
        style={{ width: "500px" }}
      >
        <div className="col-md-12">
          <div className="col-md-12 mt-3">
            <label htmlFor="validationDefaultUsername" className="form-label">
              Güncellenmek İstenen Kullanıcının Kimlik Numarası
            </label>
            <input
              onChange={(event) => setIdentificationNumber(event.target.value)}
              type="number"
              className="form-control"
              id="validationDefaultUsername"
              aria-describedby="inputGroupPrepend2"
              required
            />
          </div>
          <div className="col-md-12 mt-3">
            <label htmlFor="validationDefault05" className="form-label">
              Aylık Maaş
            </label>
            <input
              onChange={(event) => setMonthlyIncome(event.target.value)}
              type="text"
              className="form-control"
              id="validationDefault05"
              required
            />
          </div>
          <div className="col-md-12 mt-3">
            <label htmlFor="validationDefault05" className="form-label">
              Teminat
            </label>
            <input
              onChange={(event) => setAssurance(event.target.value)}
              type="text"
              className="form-control"
              id="validationDefault05"
            />
          </div>
          <div className="col-12 mt-3">
            <button className="btn btn-primary" type="submit">
              Kullanıcı Güncelle
            </button>
          </div>

          <Alert
            className="mt-3"
            show={showAlert}
            variant="warning"
            onClose={() => setShowAlert(false)}
            dismissible
          >
            Error : {errorResponse}
          </Alert>
        </div>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Güncellenmiş Başvuru Sonucu</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h4>Kredi Durumu : {applicationStatus}</h4>
            <h4>Kredi Limiti : {creditLimit}</h4>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Kapat
            </Button>
          </Modal.Footer>
        </Modal>
      </form>
    </div>
  );
}

export default UpdateCustomer;
