-- Get stories
CREATE OR REPLACE FUNCTION get_stories(ip_page_number IN int, ip_story_count IN int, ip_category_name IN varchar, ip_user_id IN bigint, op_all_rows OUT bigint, snCursor OUT refcursor) AS $$
DECLARE
	v_sql varchar;
	v_where varchar;
	v_sql_count varchar;
BEGIN
	IF ip_category_name IS NOT NULL THEN
		IF ip_user_id IS NOT NULL THEN
			v_where := 'WHERE c.name = ''' || ip_category_name || ''' AND u.user_id = ' || ip_user_id || ' ';
		ELSE
			v_where := 'WHERE c.name = ''' || ip_category_name || ''' ';
		END IF;
		
		v_sql := 'SELECT * FROM stories s ' ||
			'JOIN categories c ON s.category_id = c.category_id ' ||
			'JOIN users u ON s.user_id = u.user_id ' || v_where ||
			'ORDER BY 1 DESC LIMIT ' || ip_story_count ||
			' OFFSET ' || (ip_page_number - 1) * ip_story_count;
	END IF;
	
	IF ip_category_name IS NULL THEN
		IF ip_user_id IS NOT NULL THEN
			v_sql := 'SELECT * FROM stories s ' ||
				'JOIN categories c ON s.category_id = c.category_id ' ||
				'JOIN users u ON s.user_id = u.user_id WHERE u.user_id = ' || ip_user_id || ' ORDER BY 1 DESC LIMIT ' || ip_story_count ||
				' OFFSET ' || (ip_page_number - 1) * ip_story_count;
		ELSE
			v_sql := 'SELECT * FROM stories s ' ||
				'JOIN categories c ON s.category_id = c.category_id ' ||
				'JOIN users u ON s.user_id = u.user_id ORDER BY 1 DESC LIMIT ' || ip_story_count ||
				' OFFSET ' || (ip_page_number - 1) * ip_story_count;
		END IF;
	END IF;
	
	op_all_rows := get_stories_count(ip_category_name, ip_user_id);
	
	OPEN snCursor FOR EXECUTE v_sql;
END;
$$ LANGUAGE plpgsql;

-- Get stories count
CREATE OR REPLACE FUNCTION get_stories_count(ip_category_name IN varchar, ip_user_id IN bigint, op_all_rows OUT bigint) RETURNS BIGINT AS $$
BEGIN
	IF ip_category_name IS NOT NULL THEN
		IF ip_user_id IS NOT NULL THEN
			SELECT count(*) INTO op_all_rows FROM stories s
			JOIN categories c ON s.category_id = c.category_id
			WHERE c.name = ip_category_name
			AND user_id = ip_user_id;
		ELSE
			SELECT count(*) INTO op_all_rows FROM stories s
			JOIN categories c ON s.category_id = c.category_id
			WHERE c.name = ip_category_name;
		END IF;
	END IF;
	
	IF ip_category_name IS NULL THEN
		IF ip_user_id IS NOT NULL THEN
			SELECT count(*) INTO op_all_rows FROM stories s WHERE user_id = ip_user_id;
		ELSE
			SELECT count(*) INTO op_all_rows FROM stories s;
		END IF;
	END IF;
END;
$$ LANGUAGE plpgsql;

-- Get story comments
CREATE OR REPLACE FUNCTION get_comments(ip_story_id IN bigint, ip_page IN int, ip_limit IN int, snCursor OUT refcursor) RETURNS refcursor AS $$
BEGIN
	OPEN snCursor FOR
	SELECT c.*, u.first_name, u.last_name
	FROM comments c
	JOIN users u ON c.user_id = u.user_id
	WHERE c.story_id = ip_story_id
	ORDER BY c.created_on DESC
	LIMIT ip_page * ip_limit;
END;
$$ LANGUAGE plpgsql;