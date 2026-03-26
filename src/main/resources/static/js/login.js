document.addEventListener('DOMContentLoaded', () => {
    const formLogin = document.getElementById('form-login');

    formLogin.addEventListener('submit', (evento) => {
        evento.preventDefault();
        
        const usuario = document.getElementById('nm_usuario').value;
        
        console.log("Tentativa de login capturada para o usuário: " + usuario);
        alert("Integração com o back-end Java será feita aqui!");
        
    });
});