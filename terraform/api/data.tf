data "aws_vpc" "vpc" {
    id = var.vpc
}

data "aws_subnet" "subnet1" {
  id = "subnet-08d6e761" /* TODO - read from vars  */
  vpc_id = data.aws_vpc.vpc.id
}

data "aws_s3_bucket" "s3_bucket_artifacts" {
  bucket = var.s3_bucket_artifacts
}

data "aws_s3_bucket" "s3_bucket_logs" {
  bucket = var.s3_bucket_logs
}

data "aws_ssm_parameter" "access_token" {
  name            = var.access_token_ssm_parameter_name
  with_decryption = true
}

data "aws_sns_topic" "notification" {
  name = var.sns_topic_name
}

data "aws_iam_policy_document" "sns_topic_policy" {
  statement {
    effect  = "Allow"
    actions = ["SNS:Publish"]

    principals {
      type        = "Service"
      identifiers = ["events.amazonaws.com"]
    }

    resources = [
      data.aws_sns_topic.notification.arn
    ]
  }
}

