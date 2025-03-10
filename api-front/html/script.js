// Fonction utilitaire pour formater les données en x-www-form-urlencoded
function formEncode(data) {
    return new URLSearchParams(data).toString();
}

// Affiche le formulaire choisi et cache les autres
function afficherFormulaire(type) {
    document.getElementById('choix-container').style.display = 'none';
    document.getElementById('form-login').style.display = (type === 'login') ? 'block' : 'none';
    document.getElementById('form-signin').style.display = (type === 'signin') ? 'block' : 'none';
}

// Gestion du formulaire de connexion
document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;
    console.log(username, password)

    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
        });
        const data = await response.json();
        console.log(response)

        alert(`Connexion réussie : ${data.token}`);
        localStorage.setItem('token', data.token);
        window.location.href = 'dashboard.html';
    } catch (error) {
        alert('Erreur : ' + error.message);
    }
});

// Gestion du formulaire d'inscription
document.getElementById('signinForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('signin-username').value;
    const password = document.getElementById('signin-password').value;

    try {
        const response = await fetch('http://localhost:8080/api/auth/signin', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
        });

        if (!response.ok) throw new Error('Erreur lors de l\'inscription');
        const data = await response.json();

        alert(`Inscription réussie : ${data.token}`);
        localStorage.setItem('token', data.token);
        window.location.href = 'dashboard.html';
    } catch (error) {
        alert('Erreur : ' + error.message);
    }
});
