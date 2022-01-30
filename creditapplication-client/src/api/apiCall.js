import axios from "axios";

export const createCustomer = (body) => {
  return axios.post("/customer", body);
};

export const findApplication = (birthDate, identificationNumber) => {
  return axios.get(
    `/application?date=${birthDate}&identificationNumber=${identificationNumber}`
  );
};

export const updateCustomer = (updateRequest) => {
  return axios.put(
    `/customer/${updateRequest.identificationNumber}?monthlyIncome=${updateRequest.monthlyIncome}&assurance=${updateRequest.assurance}`
  );
};
