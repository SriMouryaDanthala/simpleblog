import { Navigate } from "react-router";

const SecureComponent = ({ children }) => {
  const isAuthenticated = Boolean(localStorage.getItem("jwt"));

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return children;
};


export default SecureComponent;