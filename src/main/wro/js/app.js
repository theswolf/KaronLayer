
$(function () {
	var Page = React.createClass({
		render: function render() {
			React.createElement(
				'div',
				null,
				'AppNavbar'
			);
		}
	});

	ReactDOM.render(React.createElement(Page, null), document.getElementById('container'));
});