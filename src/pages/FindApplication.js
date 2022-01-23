import React from 'react';

function FindApplication() {
    return (

        <div className='container' style={{ height: '20vh', width: '', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <form className="row g-3 needs-validation" noValidate>
                <div className="col-md-4">
                    <label htmlFor="validationCustom01" className="form-label">Doğum Tarihi</label>
                    <input type="text" className="form-control" id="validationCustom01" defaultValue="Mark" required />
                    <div className="valid-feedback">
                        Looks good!
                    </div>
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom02" className="form-label">Kimlik Numarası</label>
                    <input type="text" className="form-control" id="validationCustom02" defaultValue="Otto" required />
                    <div className="valid-feedback">
                        Looks good!
                    </div>
                </div>
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">Başvuruyu Bul</button>
                </div>
            </form>
        </div>
    )
}

export default FindApplication;
