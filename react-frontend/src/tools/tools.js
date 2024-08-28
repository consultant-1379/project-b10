

export function createPayload(formData, userId) {
    let results = [];
    let payload = {};
    let user = {
                "userId": userId,
                "name": formData.get("name"),
                "company": formData.get("company"),
                "email": formData.get("email")
            }
    payload["user"] = user;

    for (var [key, value] of formData.entries()) {

        if (key === "name" || key === "company" || key === "email") {
            continue;
        }


        let [areaId, questionId] = key.split("-");
        if (!questionId) {
            questionId = 0;
        }

        let entry = {
            "areaId": Number(areaId),
            "questionId": Number(questionId),
            "value": Number(value)
        }
        results.push(entry);
    }
    payload["listOfAnswers"] = results;
    return payload;
}