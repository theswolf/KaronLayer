var Navbar = ReactBootstrap.Navbar;
var NavDropdown = ReactBootstrap.NavDropdown;
var NavItem = ReactBootstrap.NavItem;
var MenuItem = ReactBootstrap.MenuItem;
var Nav = ReactBootstrap.Nav;

var AppNavbar = React.createClass({
			render: function () {
						return React.createElement(
									Navbar,
									{ inverse: true },
									React.createElement(
												Navbar.Header,
												null,
												React.createElement(
															Navbar.Brand,
															null,
															React.createElement(
																		"a",
																		{ href: "#" },
																		"React-Bootstrap"
															)
												),
												React.createElement(Navbar.Toggle, null)
									),
									React.createElement(
												Navbar.Collapse,
												null,
												React.createElement(
															Nav,
															null,
															React.createElement(
																		NavItem,
																		{ eventKey: 1, href: "#" },
																		"Link"
															),
															React.createElement(
																		NavItem,
																		{ eventKey: 2, href: "#" },
																		"Link"
															),
															React.createElement(
																		NavDropdown,
																		{ eventKey: 3, title: "Dropdown", id: "basic-nav-dropdown" },
																		React.createElement(
																					MenuItem,
																					{ eventKey: 3.1 },
																					"Action"
																		),
																		React.createElement(
																					MenuItem,
																					{ eventKey: 3.2 },
																					"Another action"
																		),
																		React.createElement(
																					MenuItem,
																					{ eventKey: 3.3 },
																					"Something else here"
																		),
																		React.createElement(MenuItem, { divider: true }),
																		React.createElement(
																					MenuItem,
																					{ eventKey: 3.3 },
																					"Separated link"
																		)
															)
												),
												React.createElement(
															Nav,
															{ pullRight: true },
															React.createElement(
																		NavItem,
																		{ eventKey: 1, href: "#" },
																		"Link Right"
															),
															React.createElement(
																		NavItem,
																		{ eventKey: 2, href: "#" },
																		"Link Right"
															)
												)
									)
						);
			}
});