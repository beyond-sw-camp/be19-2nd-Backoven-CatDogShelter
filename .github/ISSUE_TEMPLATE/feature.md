---
name: Feature
about: 기능 추가
title: Feature
labels: ''
assignees: LeeSeonggeon, dddd0ng, Easy-going12, haenin, bynmch

---

name: "✨ 기능 추가"
description: "새로운 기능을 제안하거나 추가할 때 사용하는 템플릿"
title: "[Feature] "
labels: ["feature", "enhancement"]
assignees: []

body:
  - type: textarea
    attributes:
      label: 📄 설명
      description: 새로운 기능에 대한 설명을 작성해 주세요.
      placeholder: 자세히 적을수록 좋습니다!
    validations:
      required: true

  - type: textarea
    attributes:
      label: ✅ 작업할 내용
      description: 할 일을 체크박스 형태로 작성해주세요.
      placeholder: |
        - [ ] API 설계
        - [ ] DB 스키마 변경
        - [ ] 프론트엔드 연동
    validations:
      required: true
