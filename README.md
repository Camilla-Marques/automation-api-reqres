# DesafioQA | Automação dos testes para API Reqres
## Detalhes da técnica utilizada para escrita dos casos de teste

![Alt text](https://github.com/Camilla-Marques/automation-api-reqres/blob/master/images/automation_API.jpg)

- [x] Builders\
Técnica utilizada para criar os objetos de teste com Lombok, deixando o código mais limpo e rápido.
- [x] Utilização de properties\
Utilizado para gerenciamento de propriedades da API(URI, Path...) que foi automatizada.
- [x] Sintaxe da linguagem Gherkin\
O Rest Assured por si só já utiliza a sintaxe da linguagem Gherkin amplamente usada em práticas BDD, portanto,\
ele acaba divindo em seções para demonstrar o comportamento do código. Lembrando que não há documento Feature neste projeto.
- [x] Testes de Contrato/Confiabilidade\
Foi feita a verificação se as palavras utilizadas no Json são as esperadas pela API, para isto foram feitos testes validando o nome de cada campo e os dados passados.\
Com os testes criados conseguimos validar se a API recebe corretamente os dados, realiza o processamento adequadamente e apresenta os resultados corretamente.
- [x] Testes de Funcionalidade/Caixa Preta\
Foram feitos testes para entender o comportamento da aplicação durante a utilização do usuário, ou seja,\
testando as funcionalidades da API e identificando possíveis problemas na interface.
- [x] Testes de Performance\
Foi criado teste para validar o tempo de resposta e se estaria "de pé".
- [x] Testes de Segurança\
Foram feitos testes básicos de segurança no processo de Criação, Login e Registro do user, para avaliar se seria possível realizar login ou registro sem os parâmetros necessários.


