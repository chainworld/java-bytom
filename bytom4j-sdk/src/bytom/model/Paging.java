package bytom.model;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class Paging implements java.io.Serializable {
	private static final long serialVersionUID = -3285857427993796670L;

	private int page = -1; // 页码。注意：最多返回200条分页内容。
	private int count = -1; // 指定每页返回的记录条数。
	private long sinceId = -1; // 若指定此参数，则只返回ID比since_id大（即比since_id发表时间晚的）的微博消息。
	private long maxId = -1; // 若指定此参数，则返回ID小于或等于max_id的微博消息

	public Paging() {
	}

	public Paging(int page) {
		setPage(page);
	}

	public Paging(long sinceId) {
		setSinceId(sinceId);
	}

	public Paging(int page, int count) {
		this(page);
		setCount(count);
	}

	public Paging(int page, long sinceId) {
		this(page);
		setSinceId(sinceId);
	}

	public Paging(int page, int count, long sinceId) {
		this(page, count);
		setSinceId(sinceId);
	}

	public Paging(int page, int count, long sinceId, long maxId) {
		this(page, count, sinceId);
		setMaxId(maxId);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			throw new IllegalArgumentException("page should be positive integer. passed:"
					+ page);
		}
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if (count < 1) {
			throw new IllegalArgumentException(
					"count should be positive integer. passed:" + count);
		}
		this.count = count;
	}

	public Paging count(int count) {
		setCount(count);
		return this;
	}

	public long getSinceId() {
		return sinceId;
	}

	public void setSinceId(int sinceId) {
		if (sinceId < 1) {
			throw new IllegalArgumentException(
					"since_id should be positive integer. passed:" + sinceId);
		}
		this.sinceId = sinceId;
	}

	public Paging sinceId(int sinceId) {
		setSinceId(sinceId);
		return this;
	}

	public void setSinceId(long sinceId) {
		if (sinceId < 1) {
			throw new IllegalArgumentException(
					"since_id should be positive integer. passed:" + sinceId);
		}
		this.sinceId = sinceId;
	}

	public Paging sinceId(long sinceId) {
		setSinceId(sinceId);
		return this;
	}

	public long getMaxId() {
		return maxId;
	}

	public void setMaxId(long maxId) {
		if (maxId < 1) {
			throw new IllegalArgumentException(
					"max_id should be positive integer. passed:" + maxId);
		}
		this.maxId = maxId;
	}

	public Paging maxId(long maxId) {
		setMaxId(maxId);
		return this;
	}
}
