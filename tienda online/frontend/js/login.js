document.getElementById('login-form').addEventListener('submit', (e) => {
  e.preventDefault();
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  fetch('/api/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password })
  })
    .then(res => {
      if (res.ok) return res.json();
      else throw new Error('Credenciales inválidas');
    })
    .then(data => {
      localStorage.setItem('user', JSON.stringify(data.data));
      window.location.href = 'index.html';
    })
    .catch(err => {
      document.getElementById('message').innerText = err.message;
    });
});
