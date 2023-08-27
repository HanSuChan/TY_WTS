function handleTableClick(event) {
	const priceInput = document.getElementById('orderBookPrice');
	const clickedCell = event.target;

	if (clickedCell.tagName === 'TD') {
		const cellValue = clickedCell.textContent;
			console.log(cellValue);

		priceInput.value = cellValue;
	}
}

const sellPricesTableBody = document.getElementById('sellPricesTableBody');
sellPricesTableBody.addEventListener('click', handleTableClick);

const buyPricesTableBody = document.getElementById('buyPricesTableBody');
buyPricesTableBody.addEventListener('click', handleTableClick);

const currentPrice = document.getElementById('currentPrice');
currentPrice.addEventListener('click', function(event) {
	const priceInput = document.getElementById('orderBookPrice'); 
	const currentPriceValue = event.target.textContent;
	console.log(currentPriceValue);
	priceInput.value = currentPriceValue;
});