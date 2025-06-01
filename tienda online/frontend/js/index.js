document.addEventListener('DOMContentLoaded', () => {
  fetch('/api/products')
    .then(res => res.json())
    .then(products => {
      const list = document.getElementById('product-list');
      products.forEach(p => {
        const card = document.createElement('div');
        card.className = 'product-card';
        card.innerHTML = `
          <h3>${p.nombre}</h3>
          <p>${p.descripcion}</p>
          <p>Precio: $${p.precio}</p>
          <button data-id="${p.id}" class="add-to-cart">Agregar al carrito</button>
        `;
        list.appendChild(card);
      });

      document.querySelectorAll('.add-to-cart').forEach(btn => {
        btn.addEventListener('click', () => {
          const id = btn.getAttribute('data-id');
          let cart = JSON.parse(localStorage.getItem('cart')) || [];
          cart.push({ productId: id, quantity: 1 });
          localStorage.setItem('cart', JSON.stringify(cart));
          alert('Producto agregado al carrito');
        });
      });
    });
});
