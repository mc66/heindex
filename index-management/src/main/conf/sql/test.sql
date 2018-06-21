SELECT category.name AS category, app.name AS app, app.logo, active.mau, active.basis FROM app_info app
LEFT JOIN app_category category ON app.category = category.id
LEFT JOIN app_mau active ON app.id = active.app
ORDER BY active.mau DESC
limit 1,1;


SELECT category.name AS category, app.name AS app, app.logo, active.mau, active.basis FROM app_info app
RIGHT JOIN app_category category ON app.category = category.id
LEFT JOIN app_mau active ON app.id = active.app
WHERE category.name = '移动视频类'
ORDER BY active.mau DESC
limit 1,1;