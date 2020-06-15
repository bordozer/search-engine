resource "aws_cloudwatch_log_group" "default" {
  name = "tf-${var.service_instance_name}"

  tags = local.common_tags
}
