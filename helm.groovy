node ("${podName}") {
    echo "node: ${podName}"
    stage ("helm installation") {
        def helmHelpOutput = sh(script: 'helm --help | wc -l', returnStdout: true)
        if (helmHelpOutput.toInteger() == 0) {
            echo "Helm is not installed."
            sh returnStdout: true, script: """
            cd /tmp
            wget https://storage.googleapis.com/kubernetes-helm/helm-v2.9.1-linux-amd64.tar.gz
            tar -xvzf helm-v2.9.1-linux-amd64.tar.gz
            sudo mv linux-amd64/helm /usr/local/bin/helm
            sudo chmod +x /usr/local/bin/helm
            """
        }
        else {
            echo "Helm is installed. helmHelpOutput = ${helmHelpOutput} "
        }
    }
}