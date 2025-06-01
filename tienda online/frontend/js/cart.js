document.addEventListener('DOMContentLoaded', () => {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  const container = document.getElementById('cart-items');
  if (cart.length === 0) {
    container.innerText = 'Carrito vacío';
    return;
  }
  cart.forEach((item, index) => {
    fetch(`/api/products`)
      .then(res => res.json())
      .then(products => {
        const product = products.find(p => p.id == item.productId);
        if (product) {
          const div = document.createElement('div');
          div.innerHTML = `
            <p>${product.nombre} - Cantidad: ${item.quantity} - Precio: $${product.precio}</p>
          `;
          container.appendChild(div);
        }
      });
  });

  document.getElementById('checkout-button').addEventListener('click', () => {
    alert('Funcionalidad de checkout en desarrollo');
  });
});
