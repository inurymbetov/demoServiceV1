import http from "../config/http"

const basicUrl = "/users"

const getAll = () => http.get(basicUrl)

const UserService = {
  getAll
}

export default UserService;