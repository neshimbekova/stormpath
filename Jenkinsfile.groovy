node {
  properties([parameters([string(defaultValue: 'IP', description: 'Where to build?', name: 'ENV', trim: true)])])
  stage("Clone repo"){
    git 'git@github.com:neshimbekova/stormpath-flask-sample.git'
  }
  stage('Build application') {
    sh "ssh ec2-user@${ENV} sudo yum install python-pip -y"
    sh "scp -r * ec2-user@${ENV}:/tmp"
    sh "ssh ec2-user@${ENV} sudo pip install -r /tmp/requirements.txt"

  }
  stage("App Run"){
    sh "ssh ec2-user@${ENV} python /tmp/app.py"
  }
}
