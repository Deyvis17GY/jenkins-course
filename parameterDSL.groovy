job('ejemplo-github-job-DSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm{
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
    node / gitConfigName('macloujulian')
    node / gitConfigEmail('macloujulian@gmail.com')
    }
  }
  parameters{
    stringParam('nombre', defaultValue = 'Deyvis', description = 'Parametro de cadena para el job boolean')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte'])
    booleanParam('agente', false)
  }
  triggers{
    cron('H/7 * * * *')
  }
  steps{
    shell('bash jobscript.sh')
  }
  publishers{
    mailer('backup17deyvis@gmail.com', true, true)
    slackNotifier{
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
