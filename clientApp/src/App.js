import react from "react";
import ReactDOM from 'react-dom/client';
import * as bootstrap from 'bootstrap'
import Header from "./components/commonComponents/Header";
import BlogCard from "./components/Blog/BlogCard";
import BlogCardGrid from "./components/Blog/BlogCardGrid";
import Footer from "./components/commonComponents/Footer";
import LoginForm from "./components/Auth/LoginForm";
import RegisterUser from "./components/Auth/RegisterUser";
import { createBrowserRouter, Outlet, RouterProvider } from 'react-router-dom';
import WelcomeComponent from "./components/commonComponents/WelcomeComponent";
import SecureComponent from "./components/Auth/SecureComponent";
import UserProfile from "./components/User/UserProfile";
import NewBlog from "./components/Blog/NewBlog";
import MyBlogs from "./components/Blog/MyBlogs";
import CompleteBlog from "./components/Blog/CompleteBlog";

const rootElement = ReactDOM.createRoot(document.getElementById('root'));



const App = () =>{
    return (
        <div id = "AppComponent">
            <Header/>
            <Outlet/>
            <Footer/>
        </div>
        
    );
};

const appRoutes = createBrowserRouter([
    {
        path : "/",
        element : <App/>,
        children : [
            {
                path : "/",
                element : <WelcomeComponent/>
            },
            {
                path : "/login",
                element : <LoginForm/>
            },
            {
                path : "/register",
                element : <RegisterUser/>
            },
            {
                path : "/home",
                element : <SecureComponent>
                    <BlogCardGrid/>
                </SecureComponent>
            },
            {
                path : "/profile/:userID",
                element : <SecureComponent>
                    <UserProfile/>
                </SecureComponent>
            },
            {
                path : "/newBlog",
                element : <SecureComponent>
                    <NewBlog/>
                </SecureComponent>
            },
            {
                path : "/myBlogs",
                element : <SecureComponent>
                    <MyBlogs/>
                </SecureComponent>
            },
            {
                path : "/blog/:blogID",
                element : <SecureComponent>
                    <CompleteBlog/>
                </SecureComponent>
            }

        ]
    }
]);

rootElement.render(<RouterProvider router={appRoutes}/>);