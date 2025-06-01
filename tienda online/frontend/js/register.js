document.getElementById('register-form').addEventListener('submit', (e) => {
  e.preventDefault();
  const nombre = document.getElementById('nombre').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  fetch('/api/register', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, email, password })
  })
    .then(res => res.json())
    .then(data => {
      if (data.status === 'OK') {
        window.location.href = 'login.html';
      } else {
        document.getElementById('message').innerText = 'Error al registrar';
      }
    });
});
