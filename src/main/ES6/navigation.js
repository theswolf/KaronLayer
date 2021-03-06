var Navbar = ReactBootstrap.Navbar;
var NavDropdown = ReactBootstrap.NavDropdown;
var NavItem = ReactBootstrap.NavItem;
var MenuItem = ReactBootstrap.MenuItem;
var Nav = ReactBootstrap.Nav;


var NavbarInstance = (
		  <Navbar inverse>
		    <Navbar.Header>
		      <Navbar.Brand>
		        <a href="#">React-Bootstrap</a>
		      </Navbar.Brand>
		      <Navbar.Toggle />
		    </Navbar.Header>
		    <Navbar.Collapse>
		      <Nav>
		        <NavItem eventKey={1} href="#">Link</NavItem>
		        <NavItem eventKey={2} href="#">Link</NavItem>
		        <NavDropdown eventKey={3} title="Dropdown" id="basic-nav-dropdown">
		          <MenuItem eventKey={3.1}>Action</MenuItem>
		          <MenuItem eventKey={3.2}>Another action</MenuItem>
		          <MenuItem eventKey={3.3}>Something else here</MenuItem>
		          <MenuItem divider />
		          <MenuItem eventKey={3.3}>Separated link</MenuItem>
		        </NavDropdown>
		      </Nav>
		      <Nav pullRight>
		        <NavItem eventKey={1} href="#">Link Right</NavItem>
		        <NavItem eventKey={2} href="#">Link Right</NavItem>
		      </Nav>
		    </Navbar.Collapse>
		  </Navbar>
		);

var AppNavigation = React.createClass({
	  render: function render() {
		  return NavbarInstance
	  }
	});
