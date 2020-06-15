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

data "aws_ssm_parameter" "github_access_token" {
  name            = var.personal_access_token_name
  with_decryption = true
}
