function mostrarMensagem() {
    alert("Operação realizada com sucesso!")
}

function esconderMostrarTabela() {
    const tabela = document.getElementById("tabelaRegisto");

    if (tabela.style.display === "none") {
        tabela.style.display = "table";
    } else {
        tabela.style.display = "none";
    }
}

function destacarNomes() {
    const nomes = document.querySelectorAll(".nome-registo");

    nomes.forEach(function (nome) {
        nome.classList.toggle("destacado");
    });
}

function limparTemas() {
    document.body.classList.remove(
        "tema-paciente",
        "tema-medico",
        "tema-secretaria",
        "tema-utilizador")
}

function mudarFormulario() {
    const perfil = document.getElementById("perfil");
    const tituloFormulario = document.getElementById("tituloFormulario");
    const campoEspecialidade = document.getElementById("campoEspecialidade");
    const especialidade = document.getElementById("especialidade");

    if (!perfil || !tituloFormulario || !campoEspecialidade || !especialidade) {
        return;
    }

    limparTemas();

    if (perfil.value === "PACIENTE") {
        tituloFormulario.textContent = "Novo Paciente";
        campoEspecialidade.styleDisplay = "none";
        especialidade.required = false;
        especialidade.value = "";
        document.body.classList.add("tema-paciente");


    } else if (perfil.value === "MEDICO") {
        tituloFormulario.textContent = "Novo Médico";
        campoEspecialidade.styleDisplay = "block";
        especialidade.required = true;
        document.body.classList.add("tema-medico");

    } else if (perfil.value === "SECRETARIA") {
        tituloFormulario.textContent = "Nova Secretária";
        campoEspecialidade.styleDisplay = "none";
        especialidade.required = false;
        especialidade.value = "";
        document.body.classList.add("tema-secretaria");

    } else {
        tituloFormulario.textContent = "Novo Utilizador";
        campoEspecialidade.styleDisplay = "none";
        especialidade.required = false;
        especialidade.value = "";
        document.body.classList.add("tema-utilizador");
    }

}

function mudarTituloListagem() {
    const tipoPagina = document.getElementById("tipoPagina");
    const tituloListagem = document.getElementById("tituloListagem");
    const subTituloListagem = document.getElementById("subTituloListagem");

    if (tipoPagina || !tituloListagem) {
        return;
    }

    limparTemas();

    if (tipoPagina.value === "PACIENTE") {
        tituloListagem.textContent = "Listar Pacientes";

        if (subTituloListagem) {
            tituloListagem.textContent = "Pacientes registados na clínica.";
        }
        document.body.classList.add("tema-paciente");
    }else if(tipoPagina.value === "MEDICO") {
        tituloListagem.textContent = "Listar Médicos";

        if (subTituloListagem) {
            tituloListagem.textContent = "Médicos registados na clínica.";
        }
        document.body.classList.add("tema-medico");

    }else if(tipoPagina.value === "SECRETARIA") {
        tituloListagem.textContent = "Listar Secretárias";

        if (subTituloListagem) {
            tituloListagem.textContent = "Secretárias registadas na clínica.";
        }
        document.body.classList.add("tema-secretaria");

    }else if(tipoPagina.value === "UTILIZADOR") {
        tituloListagem.textContent = "Listar Utilizadores";

        if (subTituloListagem) {
            tituloListagem.textContent = "Utilizadores registados na clínica.";
        }
        document.body.classList.add("tema-utilizador");

    }else{
        tituloListagem.textContent = "Listar";
        document.body.classList.add("tema-utilizador");
    }

    window.onload = function () {
        mudarFormulario();
        mudarTituloListagem();
    }
}