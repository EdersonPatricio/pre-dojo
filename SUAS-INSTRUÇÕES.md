Informações sobre o desenvolvimento do problema proposto
========
##1) Entendimento o problema

Após analisar o problema posposto, cheguei à conlusão que precisaria dividir as responsabilidades para o desenvolvimento ser mais eficaz e completo.
Com isso, criei a classe Ranking.java, que seria responsável por ter a lista de games. A classe Game.java, por sua vez, possua as informações referentes à cada jogo,
como o nome do jogo, o total de mortes por jogo, e um mapa que guardará o nome do jogador com seu respectivo objeto Player.
Já a classe Player.java, possue o nome do jogador, e a quantidade de assassinatos e mortes.


##2) Implementando a solução:

Após implementadas as classes mencionadas no tópico anterior( Ranking.java, Game.java, Player.java ), iniciei o desenvolvimento da classe Parser.java, que recebe um BufferedReader
para que seja efetuada a sua leitura. Apartir daí, é feito a separação de cada jogador, cada jogo, e todos atrelados entre sí( Players -> Games -> Ranking ),
seguindo a lógica de somar assasinatos quando alguém mata alguém, e somar mortes quando alguém mata alguém ou o mundo mata alguém. 

Após o desenvolvimento da classe Parser.java, desenvolvi a classe ExportFile.java, que é responsável por gerar um novo arquivo com as informações do Ranking gerado,
onde são mostrados no arquivo todos os jogos com todas as informações de cada jogador em cada jogo.

Feito isso, criei uma última classe, Execute.java, que é responsável por executar toda a aplicação, fazendo a leitura do arquivo, passado o arquivo para o método
executeParseByFile da classe Parser.java, obtendo assim um objeto Ranking, e com isso passando o Ranking para a classe ExportFile.java, a qual fará o export
do arquivo contendo todas as informações dos jogos e jogadores.

##3) Criação da classe de teste

Criei a classe Ranking.Test, onde a mesma é composta por 8 métodos, cada um com o objetivo de testar separadamente cada funcionalidade implementada.
Feito o uso do framework JUnit para a realização e execução dos testes.

Notas
======

O desenvolvimento em sí foi bem prazeroso, visto que adoro desafios. Procurei fazer da maneira mais correta e sucinta possível, e deixar a responsabilidade de cada funcionalidade separada.
Também comentei todos os métodos, e trechos dos códigos, para tornar ainda mais legível a interpretação do código.