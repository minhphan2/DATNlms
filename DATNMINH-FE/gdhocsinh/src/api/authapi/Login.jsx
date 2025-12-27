export async function login(email, password) {
  const response = await fetch('http://localhost:8080/api/users/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });
  if (!response.ok) throw new Error('Sai tài khoản hoặc mật khẩu');
  return response.json();
}