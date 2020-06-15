resource "aws_iam_role" "service_role" {
  name = "tf-${var.service_instance_name}-iam-role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "codebuild.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "policy" {
  role = aws_iam_role.service_role.name
  name = "tf-${var.service_instance_name}-policy"

  policy = <<EOF
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Resource": [
                "*"
            ],
            "Action": [
                "logs:CreateLogGroup",
                "logs:CreateLogStream",
                "logs:PutLogEvents"
            ]
        },
        {
            "Effect": "Allow",
            "Resource": [
                "${data.aws_s3_bucket.s3_bucket_artifacts.arn}",
                "${data.aws_s3_bucket.s3_bucket_artifacts.arn}/*",
                "${data.aws_s3_bucket.s3_bucket_logs.arn}",
                "${data.aws_s3_bucket.s3_bucket_logs.arn}/*"
            ],
            "Action": [
                "s3:PutObject",
                "s3:GetBucketAcl",
                "s3:GetBucketLocation"
            ]
        },
        {
            "Effect": "Allow",
            "Action": [
                "codebuild:CreateReportGroup",
                "codebuild:CreateReport",
                "codebuild:UpdateReport",
                "codebuild:BatchPutTestCases"
            ],
            "Resource": [
                "*"
            ]
        }
    ]
}
EOF
}
