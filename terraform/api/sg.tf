resource "aws_security_group" "build_sg" {
  name = "tf-${var.service_instance_name}-cb-sg"
  description = "${var.service_instance_name} CodeBuild SG"

  vpc_id = var.vpc

  # Access to everywhere
  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = [ "0.0.0.0/0" ]
  }

  tags = local.common_tags
}
