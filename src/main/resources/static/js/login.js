document.addEventListener('DOMContentLoaded', () => {
    const formLogin = document.getElementById('form-login');

    formLogin.addEventListener('submit', (evento) => {
        // Impede que a página recarregue ao clicar em "Entrar"
        evento.preventDefault();
        
        const usuario = document.getElementById('nm_usuario').value;
        
        // Simulação apenas para teste no front-end estático
        console.log("Tentativa de login capturada para o usuário: " + usuario);
        alert("Integração com o back-end Java será feita aqui!");
        
        // Futuramente, aqui faremos a chamada para o Spring Boot autenticar.
    });
});