resource "aws_cloudwatch_log_group" "default" {
  name = "tf-${var.service_instance_name}"

  tags = local.common_tags
}

resource "aws_cloudwatch_event_rule" "codebuild" {
  name        = "tf-${var.service_instance_name}-build-rule"
  description = "CodeBuild events for ${var.service_instance_name}"

  event_pattern = <<PATTERN
{
  "source": [
    "aws.codebuild"
  ],
  "detail-type": [
    "CodeBuild Build State Change"
  ]
}
PATTERN
}

resource "aws_cloudwatch_event_target" "sns" {
  rule      = aws_cloudwatch_event_rule.codebuild.name
  target_id = "SendToSNS"
  arn       = aws_sns_topic.codebuild_notification.arn
}

resource "aws_sns_topic_policy" "default" {
  arn    = aws_sns_topic.codebuild_notification.arn
  policy = data.aws_iam_policy_document.sns_topic_policy.json
}
