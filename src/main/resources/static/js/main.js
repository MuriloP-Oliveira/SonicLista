// Aguarda o HTML carregar
document.addEventListener("DOMContentLoaded", function() {
    
    const botoesExcluir = document.querySelectorAll('.btn-excluir');
    botoesExcluir.forEach(function(botao) {
        botao.addEventListener('click', function(evento) {
            evento.preventDefault();
            const urlExclusao = this.getAttribute('href');
            if (confirm("Tem certeza que deseja excluir este jogo?")) {
                window.location.href = urlExclusao;
            }
        });
    });

   
    const formCrud = document.getElementById('form-crud');
    
    if (formCrud) {
        formCrud.addEventListener('submit', function(evento) {
            const idJogo = document.querySelector('input[type="hidden"]').value;
            
            let mensagem = "";
            if (idJogo && idJogo.trim() !== "") {
                mensagem = "Deseja salvar as alterações neste jogo?";
            } else {
                mensagem = "Deseja cadastrar este novo jogo no catálogo?";
            }

            if (!confirm(mensagem)) {
                evento.preventDefault();
            }
        });
    }
});