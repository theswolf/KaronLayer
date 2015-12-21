
$(function () {
	var Page = React.createClass({
		render: function render() {
			return React.createElement(
				'div',
				null,
				'AppNavigation'
			);
		}
	});

	ReactDOM.render(React.createElement(Page, null), document.getElementById('container'));
});