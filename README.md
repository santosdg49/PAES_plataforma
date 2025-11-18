 O projeto dá continuidade à proposta iniciada na disciplina de Fundamentos de engenharia de software, onde foi escolhida a proposta de implementação de um aplicativo que unia instiuições de voluntariado e
 interessados em contribuir com doações e trabalho voluntário.
 
  O projeto foi divido em quatro microsserviços: 
  - usuario-service (Doador) -- Tem a função de cadastrar um usuario
    . GET api/plataforma/usuarios --> Lista todos os usuarios do repositório.
    . GET api/plataforma/usuarios/{id} --> Busca um usuário por id
    . POST api/plataforma/usuarios --> Cria um usuário
    
  - historico-servie -- Cria um historico para um usuario assim que ele é cadastrado e inclui eventos em sua lista de eventos de acordo com a escolha do usuario
    . POST api/plataforma/historicos --> cria um historico
    . GET api/plataforma/hisoricos/{id} --> Busca um historico por id
    . POST api/plataforma/historicos/{id}/eventos --> Adiciona um evento no historico através do id do historico
    . DELETE api/plataforma/historicos/{id}/eventos/{eventoId} --> Deleta um evento usando o id do historico e do evento a ser deletado
    . GET api/plataforma/historicos --> Lista todos os historicos do repositório
    
  - cliente-service (Instituição) -- Tem a função de cadastrar um cliente
    . GET api/plataforma/clientes --> Lista todos os clientes do repositório
    . POST api/plataforma/clientes} --> cria um cliente
    . GET api/plataforma/clientes/{id} --> Busca um cliente por id
    
  - evento-service -- Tem a função de criar um evento
    . GET api/plataforma/eventos --> Lista todos os eventos do repositório
    . GET api/plataforma/eventos/{id} --> Busca um evento por id
    . POST api/plataforma/eventos --> Cria um evento

Encapsulamento com docker:
  Todos os microsserviços possuem suas respectivas imagens para rodar os containers. 
  podem ser acessadas via docker hub nos repositório a seguir:
    . historico: https://hub.docker.com/repository/docker/diego904/historicoimage/general
    . evento: https://hub.docker.com/repository/docker/diego904/eventoservice/general
    . cliente: https://hub.docker.com/repository/docker/diego904/clienteimagecli/general
    . usuario: https://hub.docker.com/repository/docker/diego904/doadorservice/general

