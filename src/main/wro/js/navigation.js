"use strict";

Object.defineProperty(exports, "__esModule", {
	value: true
});
exports.NavbarInstance = undefined;

var _reactBootstrap = require("react-bootstrap");

var NavbarInstance = exports.NavbarInstance = React.createElement(
	_reactBootstrap.Navbar,
	{ inverse: true },
	React.createElement(
		_reactBootstrap.Navbar.Header,
		null,
		React.createElement(
			_reactBootstrap.Navbar.Brand,
			null,
			React.createElement(
				"a",
				{ href: "#" },
				"React-Bootstrap"
			)
		),
		React.createElement(_reactBootstrap.Navbar.Toggle, null)
	),
	React.createElement(
		_reactBootstrap.Navbar.Collapse,
		null,
		React.createElement(
			_reactBootstrap.Nav,
			null,
			React.createElement(
				_reactBootstrap.NavItem,
				{ eventKey: 1, href: "#" },
				"Link"
			),
			React.createElement(
				_reactBootstrap.NavItem,
				{ eventKey: 2, href: "#" },
				"Link"
			),
			React.createElement(
				_reactBootstrap.NavDropdown,
				{ eventKey: 3, title: "Dropdown", id: "basic-nav-dropdown" },
				React.createElement(
					_reactBootstrap.MenuItem,
					{ eventKey: 3.1 },
					"Action"
				),
				React.createElement(
					_reactBootstrap.MenuItem,
					{ eventKey: 3.2 },
					"Another action"
				),
				React.createElement(
					_reactBootstrap.MenuItem,
					{ eventKey: 3.3 },
					"Something else here"
				),
				React.createElement(_reactBootstrap.MenuItem, { divider: true }),
				React.createElement(
					_reactBootstrap.MenuItem,
					{ eventKey: 3.3 },
					"Separated link"
				)
			)
		),
		React.createElement(
			_reactBootstrap.Nav,
			{ pullRight: true },
			React.createElement(
				_reactBootstrap.NavItem,
				{ eventKey: 1, href: "#" },
				"Link Right"
			),
			React.createElement(
				_reactBootstrap.NavItem,
				{ eventKey: 2, href: "#" },
				"Link Right"
			)
		)
	)
); /*var Navbar = ReactBootstrap.Navbar;
   var NavDropdown = ReactBootstrap.NavDropdown;
   var NavItem = ReactBootstrap.NavItem;
   var MenuItem = ReactBootstrap.MenuItem;
   var Nav = ReactBootstrap.Nav;*/

var AppNavigation = React.createClass({
	render: function render() {
		return NavbarInstance;
	}
});