// Aguarda o HTML carregar
document.addEventListener("DOMContentLoaded", function() {

    const formsExcluir = document.querySelectorAll('.form-excluir');
    formsExcluir.forEach(function(form) {
        form.addEventListener('submit', function(evento) {
            const confirmacao = confirm("Tem certeza que deseja excluir este jogo? Essa ação não pode ser desfeita.");
            if (!confirmacao) {
                evento.preventDefault(); 
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