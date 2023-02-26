import React, {useEffect, useState} from "react";
import {IProps} from "./IProps";
import "./HomeView.scss"
import {classNameBuilder} from "../../utils/className";
import {Container, Table} from "react-bootstrap";
import UserService from "../../services/UserService";

const HomeView: React.FC<IProps> = () => {
  const cn = classNameBuilder("home")

  const [users, setUsers] = useState([])

  useEffect(() => {
    UserService.getAll()
      .then(value => setUsers(value.data))
      .catch(reason => alert(reason))
  }, [])

  return (
    <Container className={cn()}>
      <Table striped className={"mt-2"}>
        <thead>
        <tr>
          <th>#</th>
          <th>full name</th>
          <th>email</th>
        </tr>
        </thead>
        <tbody>
        {users.map((value: any) => (
          <tr>
            <td>{value.id}</td>
            <td>{value.fullName}</td>
            <td>{value.email}</td>
          </tr>
        ))}
        </tbody>
      </Table>
    </Container>
  )
}

export default HomeView;