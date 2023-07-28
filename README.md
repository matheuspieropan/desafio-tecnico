# Autenticação com Spring security 😄 

Como solicitado no exercício o sistema possui autenticação. Utilizei o security do spring para realização do mesmo.

<h3>Funcionamento:</h3> O endpoint /usuario/login espera no corpo da requisição um e-mail e senha. Ao realizar a validação, o sistema retorna um token JWT com expiração de 3 horas. Esse mesmo token deverá ser informado no cabeçalho Authorization em cada requisição.

<i>OBS: Para testar o login, é necessário se cadastrar através do endpoint /usuario </i>

---
<h3>Para facilitar:</h3> Ao inicializar a aplicação eu cadastrei manualmente um usuário para teste. Sendo assim, você não precisa necessariámente se cadastrar para testar as requisições. Você pode utilizar as credenciais abaixo: <br><br>
e-mail: admin@admin.com <br>
senha: 123

---