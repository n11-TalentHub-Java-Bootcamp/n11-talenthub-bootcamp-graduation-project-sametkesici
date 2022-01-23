import axios from "axios"

export const createUser =(body) => {
    return axios.post("/customer", body)
}