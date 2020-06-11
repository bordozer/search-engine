resource "aws_codebuild_project" "example" {
  name          = "tf-${var.service_instance_name}-build-project"
  description   = "${var.service_instance_name} build project"
  build_timeout = "10" // How long in minutes from 5 to 480 (8 hours). The default is 60 minutes
  service_role  = aws_iam_role.service_role.arn

  artifacts {
    type = "S3"
    location               = var.s3_bucket_artifacts
    path                   = var.service_name
    override_artifact_name = true
    name                   = "${var.service_instance_name}.jar"
    namespace_type         = "BUILD_ID"
    packaging              = "NONE"
  }

  cache {
    type     = "NO_CACHE"
  }

  environment {
    compute_type                = "BUILD_GENERAL1_SMALL"
    image                       = "aws/codebuild/standard:2.0"
    type                        = "LINUX_CONTAINER"
    image_pull_credentials_type = "CODEBUILD"

    environment_variable {
      name  = "ENV"
      value = var.environment_name
      type  = "PLAINTEXT"
    }
    environment_variable {
      name  = "ARTIFACT_NAME"
      value = "env-${var.service_instance_name}.jar"
      type  = "PLAINTEXT"
    }

    /*environment_variable {
      name  = "SOME_KEY2"
      value = "SOME_VALUE2"
      type  = "PARAMETER_STORE"
    }*/
  }

  logs_config {
    cloudwatch_logs {
      status      = "ENABLED"
      group_name  = "log-group"
      stream_name = "log-stream"
    }

    s3_logs {
      status   = "ENABLED"
      location = "${var.s3_bucket_logs}/${var.service_name}/${var.environment_name}"
      encryption_disabled = false
    }
  }

  source {
    type            = "GITHUB"
    location        = var.project_source
    git_clone_depth = 1
    /*auth {
      type = "OAUTH"
      resource = ""
    }*/

    git_submodules_config {
      fetch_submodules = true
    }
  }

  source_version = var.branch

  vpc_config {
    vpc_id = data.aws_vpc.vpc.id
    subnets = [
      data.aws_subnet.subnet1.id
    ]
    security_group_ids = [
      aws_security_group.build_sg.id,
    ]
  }

  tags = local.common_tags
}
