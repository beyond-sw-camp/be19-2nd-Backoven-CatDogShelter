name: "✨ 기능 추가"
description: "새로운 기능을 제안하거나 추가할 때 사용하는 템플릿"
title: "[Feature] "
labels: ["feature", "enhancement"]
assignees: []

body:
  - type: textarea
    attributes:
      label: 📄 설명

    validations:
      required: true

  - type: textarea
    attributes:
      label: ✅ 작업할 내용
        - [ ] ...
    validations:
      required: true
