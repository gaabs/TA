Requerimento: Autoavalia��o (divulga��o com prazo)

Feature A: Requisi��o de autoavalia��o.
	Como um professor da disciplina.
	Eu quero definir o prazo em que ser� poss�vel divulgar autoavalia��es e notificar os alunos.
	De modo que eles estejam avisados e divulguem-nas dentro do per�odo de tempo programado.

Cen�rio 1: Cria��o do prazo.
	Dado que estou logado no sistema na minha p�gina de perfil.
	Quando eu solicitar a cria��o de um prazo de autoavalia��o.
	Em seguida o sistema registrar� o per�odo de tempo pedido e enviar� um e-mail de notifica��o desse prazo para os alunos da disciplina.

Cen�rio 2: Edi��o de prazo.
	Dado que preciso modificar a data do prazo.
	Quando eu solicitar a edi��o do prazo de autoavalia��o.
	Em seguida, o sistema registrar� o novo per�odo de tempo pedido e enviar� um e-mail avisando sobre a altera��o aos alunos da disciplina.

Cen�rio 3: Data inexistente ou anterior ao per�odo da disciplina.
	Dado que tento criar um prazo de autoavalia��o.
	Quando registro uma data inexistente ou que existe anteriormente � data de in�cio da disciplina.
	Em seguida, o sistema me avisa do erro e solicita uma nova data.

Cen�rio 4: Data posterior ao per�odo da disciplina.
	Dado que tento criar um prazo de autoavalia��o.
	Quando registro uma data que existe posteriormente � data de in�cio da disciplina.
	Em seguida, o sistema me pergunta se desejo mesmo registrar a nova data solicitada.