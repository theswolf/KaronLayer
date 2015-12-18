var Page = React.createClass({
	  render: function render() {
		    return React.createElement(
		      "div",
		      null,
		      NavbarInstance
		    );
		  }
		});

ReactDOM.render(React.createElement(Page), document.getElementById('container'));