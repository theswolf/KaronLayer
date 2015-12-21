
$(function() {
	var Page = React.createClass({
		  render: function render() {
			  return <div><AppNavigation /></div>
		  }
		});

	ReactDOM.render(<Page />, document.getElementById('container'));
});