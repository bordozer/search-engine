resource "aws_sns_topic" "codebuild_notification" {
  name = "tf-${var.service_name}-notification"

  provisioner "local-exec" {
    command = "sh ./files/sns_subscription.sh"
    environment = {
      sns_arn = self.arn
      sns_emails = var.notification_emails
    }
  }

  tags = local.common_tags
}
