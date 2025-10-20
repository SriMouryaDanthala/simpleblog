
const Footer = () => {
    return (
        <div>
            <footer className="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
                <div className="col-md-4 d-flex align-items-center">
                    <a
                    href="/"
                    className="mb-3 me-2 mb-md-0 text-body-secondary text-decoration-none lh-1"
                    aria-label="Bootstrap"
                    >
                    {/* Replace with actual logo image */}
                    <img
                        src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/icons/bootstrap.svg"
                        alt="Bootstrap logo"
                        width="30"
                        height="24"
                    />
                    </a>
                    <span className="mb-3 mb-md-0 text-body-secondary">© 2025 Company, Inc</span>
                </div>

                <ul className="nav col-md-4 justify-content-end list-unstyled d-flex">
                    <li className="ms-3">
                    <a className="text-body-secondary" href="#" aria-label="Instagram">
                        <img
                        src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/icons/instagram.svg"
                        alt="Instagram"
                        width="24"
                        height="24"
                        />
                    </a>
                    </li>
                    <li className="ms-3">
                    <a className="text-body-secondary" href="#" aria-label="Facebook">
                        <img
                        src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/icons/facebook.svg"
                        alt="Facebook"
                        width="24"
                        height="24"
                        />
                    </a>
                    </li>
                </ul>
            </footer>
        </div>
    );
};

export default Footer;