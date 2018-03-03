package org.roof.im.message.jdbc;

import org.roof.im.message.Message;
import org.roof.im.message.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao {
    private static final String MESSAGE_SAVE_SQL = "insert into im_message (sender,receiver,type,payload,create_time,state) " +
            "values(?, ?, ?, ?, ?, ?)";
    private static final String LAST_INSERT_ID = "select LAST_INSERT_ID()";
    private static final String MESSAGE_STATE_UPDATE_SQL = "update im_message set state = ? where id = ?";
    private static final String QUERY_LATEST_SQL = "select * from im_message where receiver = ? order by create_time desc limit ?";
    private static final String QUERY_SQL = "select * from im_message where receiver = ? and create_time > ? and create_time < ? " +
            " order by create_time desc limit ?,?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Message message) {
        jdbcTemplate.update(MESSAGE_SAVE_SQL, message.getSender(), message.getReceiver(),
                message.getType(), message.getPayload(), message.getCreateTime(), message.getState());
        long id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Long.class);
        message.setId(id);
    }

    @Override
    public int updateState(Long messageId, int state) {
        return jdbcTemplate.update(MESSAGE_STATE_UPDATE_SQL, state, messageId);
    }

    @Override
    public List<Message> queryLatest(String receiver, int limit) {
        return jdbcTemplate.queryForList(QUERY_LATEST_SQL, new Object[]{receiver, limit}, Message.class);
    }

    @Override
    public List<Message> query(String receiver, Date start, Date end, int offset, int limit) {
        return jdbcTemplate.queryForList(QUERY_SQL, new Object[]{receiver, start.getTime(), end.getTime(), offset, limit}, Message.class);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
